document.addEventListener("DOMContentLoaded", () => {
    const forgotPassword = document.getElementById("password-reset");

    forgotPassword.addEventListener("click", () => {
        forgotPassword.textContent = "Lowkey that's on you - Contact Aidan Booth (password reset is £5)"
    });

    initForm()
})

function initForm() {
    const form = document.getElementById('login-form');
    form.addEventListener('submit', async (e) => {
        e.preventDefault();

        const formData = new FormData(form);
        const payload = {
            username: formData.get('username'),
            password: formData.get('password')
        };

        const response = await fetch('/api/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        });

        if (response.ok) {
            window.location.href = "/edit/home";
        } else {
            const data = await response.json();
            alert('Login failed: ' + data.message);
        }
    });
}
