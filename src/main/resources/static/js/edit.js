function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

document.addEventListener("DOMContentLoaded", () => {
    const btn = document.getElementById("save-btn");
    const successText = document.getElementById("save-success");
    const failText = document.getElementById("save-fail")

    btn.addEventListener("click", () => {
        const title = document.getElementById("title");
        const subtitle = document.getElementById("subtitle");
        const text = document.getElementById("text");

        const dto = {
            "page" : "home",
            "title" : title,
            "subtitle" : subtitle,
            "text" : text
        }

        fetch('/edit/update-content', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(dto),
        })
            .then(res => res.json())
            .then(data => {
                console.log('Success:', data);
                successText.style.opacity = '1';
                sleep(1000).then(() => successText.style.opacity = '0');
            })
            .catch(err => {
                console.error('Error:', err)
                failText.style.opacity = '1';
                sleep(1000).then(() => failText.style.opacity = '0');});
    })
})