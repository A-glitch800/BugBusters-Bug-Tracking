document.getElementById('loginForm').addEventListener('submit', function(e) {
    e.preventDefault();
    
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const userType = document.getElementById('userType').value.toLowerCase();

    // In a real application, you would validate the username and password here

    switch(userType) {
        case 'manager':
            window.location.href = 'manager.html';
            break;
        case 'developer':
            window.location.href = 'developer.html';
            break;
        case 'tester':
            window.location.href = 'tester.html';
            break;
        default:
            alert('Invalid user type. Please enter manager, developer, or tester.');
    }
});