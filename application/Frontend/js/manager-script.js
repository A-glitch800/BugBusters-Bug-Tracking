document.addEventListener('DOMContentLoaded', function() {
    const viewProjectLink = document.getElementById('viewProjectLink');
    
    viewProjectLink.addEventListener('click', function(e) {
        e.preventDefault();
        window.location.href = 'project-description.html';
    });
});