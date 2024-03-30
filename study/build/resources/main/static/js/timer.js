// main.js

let timerInterval; // setInterval을 저장하기 위한 변수
let startTime; // 시작 시간을 저장하기 위한 변수
let running = false; // 타이머가 실행 중인지 여부를 저장하는 변수

// 타이머 시작 함수
function startTimer() {
    startTime = new Date();
    timerInterval = setInterval(updateTimer, 1000);
    running = true;
}

// 타이머 업데이트 함수
function updateTimer() {
    let currentTime = new Date();
    let elapsedTime = currentTime - startTime;
    let seconds = Math.floor((elapsedTime / 1000) % 60);
    let minutes = Math.floor((elapsedTime / 1000 / 60) % 60);
    let hours = Math.floor((elapsedTime / 1000 / 60 / 60) % 24);

    // 10보다 작은 경우 0을 추가하여 두 자리로 표시
    seconds = seconds < 10 ? "0" + seconds : seconds;
    minutes = minutes < 10 ? "0" + minutes : minutes;
    hours = hours < 10 ? "0" + hours : hours;

    // 타이머 업데이트
    document.getElementById("timer").textContent = hours + ":" + minutes + ":" + seconds;
}

// 타이머 정지 함수
function stopTimer() {
    clearInterval(timerInterval);
    running = false;
}

// 시작 버튼 클릭 시 이벤트 핸들러
document.getElementById("startButton").addEventListener("click", function() {
    if (!running) {
        startTimer();
    }
});

// 종료 버튼 클릭 시 이벤트 핸들러
document.getElementById("stopButton").addEventListener("click", function() {
    if (running) {
        stopTimer();
    }
});

function deleteSubject() {
    var selectedId = document.querySelector('#subjectselect').value;
    if(selectedId !== "") {
        var form = document.getElementById('deleteForm');
        form.action = '/timer/del/' + selectedId;
        form.method = 'POST';
        form.submit();
    } else {
        alert('과목을 선택해주세요.');
    }
}