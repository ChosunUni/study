document.addEventListener("DOMContentLoaded", function() {
    var calendarBody = document.getElementById("calendar-body");
    var currentTitle = document.getElementById("current-year-month");
    var today = new Date();
    var first = new Date(today.getFullYear(), today.getMonth(), 1);
    var dayList = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
    var monthList = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
    var leapYear = [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    var notLeapYear = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    var pageFirst = first;
    var pageYear;
    if(first.getFullYear() % 4 === 0){
        pageYear = leapYear;
    } else {
        pageYear = notLeapYear;
    }

    function showCalendar() {
        let monthCnt = 100; // 수정: 각 달의 첫째 주를 구분하기 위한 값
        let cnt = 1;
        for (var i = 0; i < 6; i++) {
            var $tr = document.createElement('tr');
            $tr.setAttribute('id', monthCnt);
            for (var j = 0; j < 7; j++) {
                if ((i === 0 && j < first.getDay()) || cnt > pageYear[first.getMonth()]) {
                    var $td = document.createElement('td');
                    $tr.appendChild($td);
                } else {
                    var $td = document.createElement('td');
                    $td.textContent = cnt;
                    $td.setAttribute('id', cnt);
                    $tr.appendChild($td);
                    cnt++;
                }
            }
            monthCnt++;
            calendarBody.appendChild($tr);
        }
    }
    showCalendar();

    function addCircleBorder() {
        var clickedDate = event.target;
        if (clickedDate.tagName === "TD") {
            var clickedDate1 = document.querySelector(".active");
            if (clickedDate1) {
                clickedDate1.classList.remove("active");
            }
            clickedDate.classList.add("active");
        }
    }

    function removeCalendar() {
        let catchTr = 100;
        for (var i = 100; i < 106; i++) {
            var $tr = document.getElementById(catchTr);
            $tr.remove();
            catchTr++;
        }
    }

    function prev() {
        removeCalendar();
        if (pageFirst.getMonth() === 1) {
            pageFirst = new Date(first.getFullYear() - 1, 12, 1);
        } else {
            pageFirst = new Date(first.getFullYear(), first.getMonth() - 1, 1);
        }
        today = new Date(today.getFullYear(), today.getMonth() - 1, today.getDate());
        currentTitle.innerHTML = monthList[first.getMonth()] + '&nbsp;&nbsp;&nbsp;&nbsp;' + first.getFullYear();
        removeCalendar();
        showCalendar();
        showMain();
        var clickedDate1 = document.getElementById(today.getDate());
        clickedDate1.classList.add('active');
    }

    function next() {
        removeCalendar();
        if (pageFirst.getMonth() === 12) {
            pageFirst = new Date(first.getFullYear() + 1, 1, 1);
        } else {
            pageFirst = new Date(first.getFullYear(), first.getMonth() + 1, 1);
        }
        today = new Date(today.getFullYear(), today.getMonth() + 1, today.getDate());
        currentTitle.innerHTML = monthList[first.getMonth()] + '&nbsp;&nbsp;&nbsp;&nbsp;' + first.getFullYear();
        removeCalendar();
        showCalendar();
        showMain();
        var clickedDate1 = document.getElementById(today.getDate());
        clickedDate1.classList.add('active');
    }

    function showMain(){
        mainTodayDay.innerHTML = dayList[today.getDay()];
        mainTodayDate.innerHTML = today.getDate();
    }

    var clickedDate1 = document.getElementById(today.getDate());
    clickedDate1.classList.add('active');
    var prevBtn = document.getElementById('prev');
    var nextBtn = document.getElementById('next');
    prevBtn.addEventListener('click',prev);
    nextBtn.addEventListener('click',next);
    var tdGroup = [];
    function clickStart(){
        for(let i = 1; i <= pageYear[first.getMonth()]; i++){
            tdGroup[i] = document.getElementById(i);
            tdGroup[i].addEventListener('click',changeToday);
        }
    }
    function changeToday(e){
        for(let i = 1; i <= pageYear[first.getMonth()]; i++){
            if(tdGroup[i].classList.contains('active')){
                tdGroup[i].classList.remove('active');
            }
        }
        clickedDate1 = e.currentTarget;
        clickedDate1.classList.add('active');
        today = new Date(today.getFullYear(), today.getMonth(), clickedDate1.id);
        showMain();
        keyValue = today.getFullYear() + '' + today.getMonth()+ '' + today.getDate();
    }
    clickStart();
});

