const qs = (s) => document.querySelector(s);
const qsa = (s) => document.querySelectorAll(s);

const API_BASE = "/api";

const state = {
  user: null,
  sessionID: "session123",
  grade: "2nd Grade",
  level: 2,
  type: "addition",
  questionIndex: 0,
  correct: 0,
  incorrect: 0,
  points: 120,
  seconds: 105,
  timer: null,
  questions: [],
  last: {
    correct: 0,
    incorrect: 0,
    points: 0,
    grade: "None",
    type: "None"
  }
};

const gradeProfiles = {
  "Kindergarten": {
    level: 1,
    emoji: "🧸",
    badge: "Starter",
    focus: "Counting & numbers",
    difficulty: "Level 1",
    description: "A gentle practice page for counting, small addition, and first subtraction facts.",
    skills: ["Count objects and recognize numbers 1–10", "Add small numbers", "Subtract with pictures"],
    types: ["counting", "addition", "subtraction"]
  },
  "1st Grade": {
    level: 1,
    emoji: "🐣",
    badge: "Beginner",
    focus: "Number sense",
    difficulty: "Level 1",
    description: "Practice addition and subtraction within 20.",
    skills: ["Add numbers within 20", "Subtract numbers within 20", "Review mixed facts"],
    types: ["addition", "subtraction", "mixed"]
  },
  "2nd Grade": {
    level: 2,
    emoji: "🚀",
    badge: "Developing",
    focus: "Fluency",
    difficulty: "Level 2",
    description: "Strengthen bigger addition and subtraction with early multiplication.",
    skills: ["Add larger numbers", "Subtract larger numbers", "Practice early multiplication"],
    types: ["addition", "subtraction", "multiplication", "mixed"]
  },
  "3rd Grade": {
    level: 3,
    emoji: "📘",
    badge: "Intermediate",
    focus: "Multiplication & division",
    difficulty: "Level 3",
    description: "Build multiplication and division fluency.",
    skills: ["Multiply facts up to 12", "Divide with related facts", "Solve mixed operations"],
    types: ["multiplication", "division", "addition", "subtraction", "mixed"]
  },
  "4th Grade": {
    level: 4,
    emoji: "🧠",
    badge: "Advanced",
    focus: "Multi-digit operations",
    difficulty: "Level 4",
    description: "Practice larger numbers and stronger mixed review.",
    skills: ["Add and subtract larger numbers", "Multiply stronger facts", "Divide confidently"],
    types: ["addition", "subtraction", "multiplication", "division", "mixed"]
  },
  "5th Grade": {
    level: 5,
    emoji: "🏆",
    badge: "Mastery",
    focus: "Mastery review",
    difficulty: "Level 5",
    description: "Review all core operations with stronger speed and accuracy.",
    skills: ["Review all operations", "Build speed", "Prepare for advanced math"],
    types: ["addition", "subtraction", "multiplication", "division", "mixed"]
  }
};

const operationInfo = {
  counting: { symbol: "#", title: "Counting", text: "Count and identify numbers." },
  addition: { symbol: "+", title: "Addition", text: "Practice adding numbers." },
  subtraction: { symbol: "−", title: "Subtraction", text: "Practice subtracting numbers." },
  multiplication: { symbol: "×", title: "Multiplication", text: "Practice multiplying numbers." },
  division: { symbol: "÷", title: "Division", text: "Practice dividing numbers." },
  mixed: { symbol: "⇄", title: "Mixed", text: "Practice several operations together." }
};

function show(id) {
  stopTimer();
  qsa(".screen").forEach((screen) => screen.classList.remove("active"));
  const page = qs("#" + id);
  if (page) page.classList.add("active");

  const nav = qs("#siteNav");
  if (nav) nav.classList.remove("open");

  if (id === "practiceView") startTimer();
  updateHeader();
}

function updateHeader() {
  const btn = qs("#loginNavBtn");
  if (btn) btn.textContent = state.user ? "Logout" : "Login";
}

async function callJavaApi(path, payload) {
  const res = await fetch(`${API_BASE}${path}`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(payload)
  });

  if (!res.ok) throw new Error("Backend error");
  return await res.text();
}

async function javaLogin(userId, password) {
  return callJavaApi("/login", { userId, password });
}

async function javaLogout(userId) {
  return callJavaApi("/logout", { userId });
}

async function javaCreatePractice(userId, grade, operation) {
  return callJavaApi("/practice", { userId, grade, operation });
}

async function javaSubmitAnswer(sessionId, question, answer, expectedAnswer) {
  return callJavaApi("/answer", {
    sessionId,
    question,
    answer: String(answer),
    expectedAnswer: String(expectedAnswer)
  });
}

function setLoginMessage(text, type = "") {
  const el = qs("#loginMessage");
  if (!el) return;
  el.textContent = text;
  el.className = "message " + type;
}

function clearLoginErrors() {
  const userError = qs("#userError");
  const passwordError = qs("#passwordError");

  if (userError) userError.textContent = "";
  if (passwordError) passwordError.textContent = "";

  qs("#userId")?.classList.remove("input-error");
  qs("#password")?.classList.remove("input-error");

  setLoginMessage("");
}

function showFieldError(field, message) {
  const input = field === "user" ? qs("#userId") : qs("#password");
  const error = field === "user" ? qs("#userError") : qs("#passwordError");

  if (input) input.classList.add("input-error");
  if (error) error.textContent = message;
}

function requireLogin() {
  if (!state.user) {
    show("loginView");
    setLoginMessage("Please log in first. Demo: student1 / Pass123!", "error");
    return false;
  }
  return true;
}

function headerNavigate(target) {
  if (target === "home") show("homeView");

  if (target === "login") {
    show(state.user ? "logoutView" : "loginView");
  }

  if (target === "practice") {
    if (requireLogin()) show("gradesView");
  }

  if (target === "progress") {
    if (requireLogin()) {
      updateProgressStats();
      show("progressView");
    }
  }
}

function rand(min, max) {
  return Math.floor(Math.random() * (max - min + 1)) + min;
}

function choice(arr) {
  return arr[Math.floor(Math.random() * arr.length)];
}

function typeName(type) {
  return {
    counting: "Counting",
    addition: "Addition",
    subtraction: "Subtraction",
    multiplication: "Multiplication",
    division: "Division",
    mixed: "Mixed Operations"
  }[type] || type;
}

function activeOps() {
  return gradeProfiles[state.grade]?.types || ["addition", "subtraction", "multiplication", "division", "mixed"];
}

function chooseGrade(grade, level) {
  state.grade = grade;
  state.level = Number(level);
  renderGradePage();
  show("gradePathView");
}

function renderGradePage() {
  const profile = gradeProfiles[state.grade];
  if (!profile) return;

  qs("#gradeBadge").textContent = `${profile.badge} • ${state.grade}`;
  qs("#gradePathTitle").textContent = `${state.grade} Practice Page`;
  qs("#gradePathDescription").textContent = profile.description;
  qs("#gradeHeroEmoji").textContent = profile.emoji;
  qs("#gradeFocus").textContent = profile.focus;
  qs("#gradeDifficulty").textContent = profile.difficulty;

  qs("#gradeSkillsList").innerHTML = profile.skills
    .map((skill) => `<li>${skill}</li>`)
    .join("");

  qs("#gradePracticeLinks").innerHTML = profile.types
    .map((type) => {
      const op = operationInfo[type];
      return `
        <button class="practice-link-card" type="button" data-type="${type}">
          <span>${op.symbol}</span>
          <strong>${op.title}</strong>
          <small>${op.text}</small>
        </button>
      `;
    })
    .join("");

  qsa(".practice-link-card").forEach((btn) => {
    btn.addEventListener("click", () => {
      state.type = btn.dataset.type;
      newPractice();
    });
  });
}

function makeQuestion() {
  let a, b, answer, op;

  op = state.type === "mixed"
    ? choice(activeOps().filter((t) => t !== "mixed"))
    : state.type;

  const max =
    state.grade === "Kindergarten" ? 10 :
    state.level === 1 ? 20 :
    state.level === 2 ? 50 :
    state.level === 3 ? 100 :
    state.level === 4 ? 250 : 500;

  if (op === "counting") {
    answer = rand(1, 10);
    return {
      label: `Count the stars: ${"★".repeat(answer)} = ?`,
      answer,
      hint: "Count each star one by one."
    };
  }

  if (op === "addition") {
    a = rand(1, max);
    b = rand(1, max);
    answer = a + b;
    return { label: `${a} + ${b} = ?`, answer, hint: `Add ${a} and ${b}.` };
  }

  if (op === "subtraction") {
    a = rand(1, max);
    b = rand(1, a);
    answer = a - b;
    return { label: `${a} − ${b} = ?`, answer, hint: `Start at ${a} and count down ${b}.` };
  }

  if (op === "multiplication") {
    a = rand(2, 12);
    b = rand(2, 12);
    answer = a * b;
    return { label: `${a} × ${b} = ?`, answer, hint: `Think of ${a} groups of ${b}.` };
  }

  b = rand(2, 12);
  answer = rand(2, 12);
  a = b * answer;
  return { label: `${a} ÷ ${b} = ?`, answer, hint: `What number times ${b} gives ${a}?` };
}

async function newPractice() {
  if (!requireLogin()) return;

  try {
    await javaCreatePractice(state.user, state.grade, state.type);
  } catch (err) {
    console.warn("Backend practice endpoint not available, using frontend practice.");
  }

  state.questionIndex = 0;
  state.correct = 0;
  state.incorrect = 0;
  state.points = 120;
  state.seconds = 105;
  state.questions = Array.from({ length: 10 }, makeQuestion);

  qs("#mascotEmoji").textContent = gradeProfiles[state.grade]?.emoji || "🚀";

  renderQuestion();
  show("practiceView");
}

function renderQuestion() {
  const q = state.questions[state.questionIndex];

  qs("#levelBadge").textContent = `${state.grade} • ${typeName(state.type)}`;
  qs("#points").textContent = state.points;
  qs("#questionText").innerHTML = q.label.replace("= ?", "= <span>?</span>");
  qs("#hintText").textContent = "";
  qs("#answerMessage").textContent = "";

  const options = new Set([q.answer]);

  while (options.size < 4) {
    let delta = rand(-10, 10);
    if (delta === 0) delta = 1;
    options.add(Math.max(0, q.answer + delta));
  }

  const shuffled = [...options].sort(() => Math.random() - 0.5);

  qs("#answersGrid").innerHTML = shuffled
    .map((answer) => `<button class="answer-btn" type="button" data-answer="${answer}">${answer}</button>`)
    .join("");

  qs("#dots").innerHTML = state.questions
    .map((_, i) => `
      <div class="dot-wrap">
        Q${i + 1}
        <span class="dot ${i < state.questionIndex ? "done" : ""}"></span>
      </div>
    `)
    .join("");

  qs("#progressFill").style.width = `${(state.questionIndex / state.questions.length) * 100}%`;

  qsa(".answer-btn").forEach((btn) => {
    btn.addEventListener("click", () => {
      submitAnswer(Number(btn.dataset.answer), btn);
    }, { once: true });
  });
}

async function submitAnswer(answer, btn) {
  qsa(".answer-btn").forEach((b) => b.disabled = true);

  const q = state.questions[state.questionIndex];
  let result;

  try {
    result = await javaSubmitAnswer(state.sessionID, q.label, answer, q.answer);
  } catch (err) {
    result = Number(answer) === Number(q.answer) ? "Correct" : "Incorrect";
  }

  const message = qs("#answerMessage");

  if (result === "Correct") {
    state.correct++;
    state.points += 10;
    btn.classList.add("correct");
    message.textContent = "Correct! 🎉";
    message.className = "answer-message success";
  } else {
    state.incorrect++;
    state.points = Math.max(0, state.points - 5);
    btn.classList.add("incorrect");
    message.textContent = `Incorrect. Correct answer: ${q.answer}`;
    message.className = "answer-message error";
  }

  qs("#points").textContent = state.points;

  setTimeout(nextQuestion, 850);
}

function nextQuestion() {
  state.questionIndex++;

  if (state.questionIndex >= state.questions.length) {
    finishPractice();
    return;
  }

  renderQuestion();
}

function finishPractice() {
  stopTimer();

  state.last = {
    correct: state.correct,
    incorrect: state.incorrect,
    points: state.points,
    grade: state.grade,
    type: typeName(state.type)
  };

  qs("#progressFill").style.width = "100%";
  qs("#correctCount").textContent = state.correct;
  qs("#incorrectCount").textContent = state.incorrect;

  updateResultMessage();
  updateProgressStats();

  show("resultView");
}

function updateResultMessage() {
  const total = state.correct + state.incorrect;
  const accuracy = total ? Math.round((state.correct / total) * 100) : 0;

  const title = qs("#resultTitle");
  const message = qs("#resultMessage");

  if (accuracy >= 90) {
    title.textContent = "Excellent work 🏆";
    message.textContent = "You mastered this session. Keep challenging yourself.";
  } else if (accuracy >= 70) {
    title.textContent = "Great progress 🎉";
    message.textContent = "Strong work. Review the missed questions and try again.";
  } else if (accuracy >= 50) {
    title.textContent = "Good effort 💪";
    message.textContent = "You are getting there. Practice one more round to improve.";
  } else if (accuracy >= 30) {
    title.textContent = "Keep practicing 🌱";
    message.textContent = "This skill needs more practice. Try again slowly with hints.";
  } else {
    title.textContent = "Let’s try again 📘";
    message.textContent = "No worries. Review the basics, use hints, and start a new practice.";
  }
}

function updateProgressStats() {
  const total = state.last.correct + state.last.incorrect;

  qs("#progressCorrect").textContent = state.last.correct;
  qs("#progressIncorrect").textContent = state.last.incorrect;
  qs("#progressAccuracy").textContent = total ? `${Math.round((state.last.correct / total) * 100)}%` : "0%";

  qs("#progressIntro").textContent = total
    ? `${state.last.grade} • ${state.last.type} • ${state.last.points} points`
    : "Complete a practice session to see your score.";
}

function startTimer() {
  stopTimer();

  qs("#timer").textContent = formatTime(state.seconds);

  state.timer = setInterval(() => {
    state.seconds--;
    qs("#timer").textContent = formatTime(state.seconds);

    if (state.seconds <= 0) finishPractice();
  }, 1000);
}

function stopTimer() {
  if (state.timer) clearInterval(state.timer);
  state.timer = null;
}

function formatTime(seconds) {
  return `${String(Math.floor(seconds / 60)).padStart(2, "0")}:${String(seconds % 60).padStart(2, "0")}`;
}

function setupButtons() {
  qsa("[data-nav]").forEach((btn) => {
    btn.addEventListener("click", () => headerNavigate(btn.dataset.nav));
  });

  qs("#menuToggle")?.addEventListener("click", () => {
    qs("#siteNav")?.classList.toggle("open");
  });

  qs("#getStartedBtn")?.addEventListener("click", () => show("loginView"));

  qs("#loginForm")?.addEventListener("submit", async (e) => {
    e.preventDefault();
    clearLoginErrors();

    const id = qs("#userId").value.trim();
    const pwd = qs("#password").value;

    if (!id) {
      showFieldError("user", "User ID is required.");
      return;
    }

    if (!pwd) {
      showFieldError("password", "Password is required.");
      return;
    }

    try {
      const result = await javaLogin(id, pwd);

      if (result !== "Login Successful") {
        if (result.toLowerCase().includes("user")) showFieldError("user", result);
        else if (result.toLowerCase().includes("password")) showFieldError("password", result);
        else setLoginMessage(result, "error");
        return;
      }

      state.user = id;
      state.sessionID = "session123";

      setLoginMessage("Login Successful", "success");
      show("gradesView");
    } catch (err) {
      setLoginMessage("Backend not reachable. Make sure Spring Boot is running.", "error");
    }
  });

  qs("#userId")?.addEventListener("input", clearLoginErrors);
  qs("#password")?.addEventListener("input", clearLoginErrors);

  qsa(".grade-card").forEach((card) => {
    card.addEventListener("click", () => {
      chooseGrade(card.dataset.grade, card.dataset.level);
    });
  });

  qs("#backToGrades")?.addEventListener("click", () => show("gradesView"));
  qs("#backToTypes")?.addEventListener("click", () => show("gradePathView"));

  qs("#hintBtn")?.addEventListener("click", () => {
    const q = state.questions[state.questionIndex];
    qs("#hintText").textContent = q?.hint || "Try breaking the problem into smaller steps.";
  });

  qs("#skipBtn")?.addEventListener("click", () => {
    state.incorrect++;
    nextQuestion();
  });

  qs("#newPracticeBtn")?.addEventListener("click", () => show("gradePathView"));

  qs("#progressPracticeBtn")?.addEventListener("click", () => {
    if (requireLogin()) show("gradesView");
  });

  qs("#yesLogout")?.addEventListener("click", async () => {
    if (state.user) {
      try {
        await javaLogout(state.user);
      } catch (err) {
        console.warn("Logout backend not reachable.");
      }
    }

    state.user = null;
    state.sessionID = null;
    qs("#loginForm")?.reset();
    setLoginMessage("You have been logged out.", "success");
    show("loginView");
  });

  qs("#cancelLogout")?.addEventListener("click", () => show("homeView"));

  qs("#forgotBtn")?.addEventListener("click", () => show("forgotView"));
  qs("#createAccountBtn")?.addEventListener("click", () => show("createAccountView"));
  qs("#backToLoginFromForgot")?.addEventListener("click", () => show("loginView"));
  qs("#backToLoginFromCreate")?.addEventListener("click", () => show("loginView"));

  qs("#forgotForm")?.addEventListener("submit", (e) => {
    e.preventDefault();
    const box = qs("#forgotSuccess");
    box.classList.add("show");
    box.textContent = "Demo recovery: use student1 / Pass123!";
  });

  qs("#createAccountForm")?.addEventListener("submit", (e) => {
    e.preventDefault();
    const box = qs("#createSuccess");
    box.classList.add("show");
    box.textContent = "Demo account creation saved for this browser session.";
  });

  qsa("[data-toggle-password]").forEach((btn) => {
    btn.addEventListener("click", () => {
      const input = qs("#" + btn.dataset.togglePassword);
      if (!input) return;

      const hidden = input.type === "password";
      input.type = hidden ? "text" : "password";
      btn.textContent = hidden ? "Hide" : "Show";
    });
  });
}

setupButtons();
show("homeView");