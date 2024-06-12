
    const main = document.querySelector("#main");
    const qna = document.querySelector("#qna");
    const result = [document.querySelector("#result")];

    const endPoint = 16;
    const select = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];

    function calResult() {
        let result = []; // 배열로 변경
        for (let i = 0; i < endPoint; i++) {
            result.push(select[i]); // 배열에 값을 추가
        }
        return result.indexOf(Math.max(...result)); // 배열에서 indexOf 함수 사용
    }


    function setResult() {
        let point = calResult();
        //let resultPage;

        location.href = "/MBTI/resultPage?point="+point;
        /*switch (point) {
            case 0:
                location.href = "/img/mbti/page/result-0.html";
                break;
            case 1:
                location.href = "/MBTI/resultPage?point="+point;
                break;
            case 2:
                location.href = "/img/mbti/page/result-2.html";
                break;
            case 3:
                location.href = "/img/mbti/page/result-3.html";
                break;
            case 4:
                location.href = "/img/mbti/page/result-4.html";
                break;
            case 5:
                location.href = "/img/mbti/page/result-5.html";
                break;
            case 6:
                location.href = "/img/mbti/page/result-6.html";
                break;
            case 7:
                location.href = "/img/mbti/page/result-7.html";
                break;
            case 8:
                location.href = "/img/mbti/page/result-8.html";
                break;
            case 9:
                location.href = "/img/mbti/page/result-9.html";
                break;
            case 10:
                location.href = "/img/mbti/page/result-10.html";
                break;
            case 11:
                location.href = "/img/mbti/page/result-11.html";
                break;
            case 12:
                location.href = "/img/mbti/page/result-12.html";
                break;
            case 13:
                location.href = "/img/mbti/page/result-13.html";
                break;
            case 14:
                location.href = "/img/mbti/page/result-14.html";
                break;
            case 15:
                location.href = "/img/mbti/page/result-15.html";
                break;
        }*/
        // 결과 페이지로 이동하는 링크 생성
        const resultLink = document.createElement('a');
        resultLink.href = resultPage;
        resultLink.textContent = '결과 페이지로 이동'; // 링크 텍스트 수정 가능

        // 결과 페이지를 표시할 DOM 요소에 링크를 추가
        const resultContainer = document.querySelector('#result'); // 결과를 표시할 요소에 맞게 수정
        resultContainer.appendChild(resultLink);
    }


    function goResult() {
        qna.style.WebkitAnimation = "fadeOut 1s";
        qna.style.animation = "fadeOut 1s";
        setTimeout(() => {
            result.style.WebkitAnimation = "fadeIn 1s";
            result.style.animation = "fadeIn 1s";
            setTimeout(() => {
                qna.style.display = "none";
                result.style.display = "block"
            }, 450)
        })
        setResult();
    }

    function addAnswer(answerText, qIdx, idx) {
        var a = document.querySelector('.answerBox');
        var answer = document.createElement('button');
        answer.classList.add('answerList');
        answer.classList.add('my-3');
        answer.classList.add('py-3');
        answer.classList.add('mx-auto');
        answer.classList.add('fadeIn');

        a.appendChild(answer);
        answer.innerHTML = answerText;

        answer.addEventListener("click", function () {
            var children = document.querySelectorAll('.answerList');
            for (let i = 0; i < children.length; i++) {
                children[i].disabled = true;
                children[i].style.WebkitAnimation = "fadeOut 0.5s";
                children[i].style.animation = "fadeOut 0.5s";
            }
            setTimeout(() => {
                var target = qnaList[qIdx].a[idx].type;
                for (let i = 0; i < target.length; i++) {
                    select[target[i]] += 1;
                }

                for (let i = 0; i < children.length; i++) {
                    children[i].style.display = 'none';
                }
                goNext(++qIdx);
            }, 450)
        }, false);
    }

    function goNext(qIdx) {
        if (qIdx === endPoint) {
            goResult();
            return;
        }

        var q = document.querySelector('.qBox');
        q.innerHTML = qnaList[qIdx].q;
        for (let i in qnaList[qIdx].a) {
            addAnswer(qnaList[qIdx].a[i].answer, qIdx, i);
        }
        var status = document.querySelector('.statusBar');
        status.style.width = (100 / endPoint) * (qIdx + 1) + '%';
    }


    function begin() {
        main.style.WebkitAnimation = "fadeOut 1s";
        main.style.animation = "fadeOut 1s";
        setTimeout(() => {
            qna.style.WebkitAnimation = "fadeIn 1s";
            qna.style.animation = "fadeIn 1s";
            setTimeout(() => {
                main.style.display = "none";
                qna.style.display = "block"
            }, 450)
            let qIdx = 0;
            goNext(qIdx);
        }, 450);
    }
    function moveHome() {
        location.href = "../mbti/mbti_main.html";
    }

