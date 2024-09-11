function toggleAttendance() {
    fetch('./toggleAttendance.do', {
        method: 'POST'
    })
    .then(response => response.json())
    .then(data => {
        alert(data.message);
        updateButtonLabel(data.isClockedIn); // 서버에서 반환된 상태를 사용합니다.
    })
    .catch(error => console.error('Error:', error));
}

function updateButtonLabel(isClockedIn) {
    const button = document.getElementById('attendanceButton');
    if (isClockedIn) {
        button.textContent = '퇴근';
        button.classList.remove('btn-primary');
        button.classList.add('btn-danger');
    } else {
		button.textContent = '출근';
		button.classList.remove('btn-danger');
        button.classList.add('btn-primary');
    }
}

function resetButton() {
    const now = new Date();
    const nextDay = new Date();
    nextDay.setHours(7, 0, 0, 0);
    nextDay.setDate(nextDay.getDate() + 1); // 다음 날로 설정
    
    if (now >= nextDay) {
        // 서버에서 상태를 직접 요청하여 확인합니다.
        fetch('./checkAttendanceStatus.do')
        .then(response => response.json())
        .then(data => {
            updateButtonLabel(data.isClockedIn);
        })
        .catch(error => console.error('Error:', error));
    }
}

document.addEventListener('DOMContentLoaded', (event) => {
    fetch('./checkAttendanceStatus.do')
    .then(response => response.json())
    .then(data => {
        updateButtonLabel(data.isClockedIn);
        resetButton();
    })
    .catch(error => console.error('Error:', error));
});
