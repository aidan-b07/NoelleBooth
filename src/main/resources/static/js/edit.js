const csrfToken = document.querySelector("meta[name='csrf-token']").content;
const csrfHeader = document.querySelector("meta[name='csrf-header']").content

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

const successText = document.getElementById("save-success");
function success(data) {
    console.log('Success:', data);
    successText.style.opacity = '1';
    sleep(1000).then(() => successText.style.opacity = '0');
}
const failText = document.getElementById("save-fail")
function fail(err) {
        console.error('Error:', err)
        failText.style.opacity = '1';
        sleep(1000).then(() => failText.style.opacity = '0');
}

function getDto(page) {
    // Select all elements that have a class starting with 'editable-'
    const allEls = document.querySelectorAll("[class*='editable-']");

    // Regex to match classes like 'editable-x' or 'editable-xx' where x is any char
    const regex = /^editable-\w{1,2}$/;

    let dto = { page };

    Array.from(allEls).forEach(el => {
        // Find the matching class in el.classList
        const matchedClass = Array.from(el.classList).find(cls => regex.test(cls));
        if (matchedClass) {
            if (!el.id) {
                console.warn("Editable element without id:", el);
                return;
            }
            const val = ("value" in el) ? el.value : el.textContent;
            dto[el.id] = val;
        }
    });

    return dto;
}


document.addEventListener("DOMContentLoaded", () => {
    const btn = document.getElementById("save-btn");

    btn.addEventListener("click", () => {

        let dto = getDto(page);

        fetch('/edit/update-content', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                [csrfHeader] : csrfToken
            },
            body: JSON.stringify(dto),
        })
            .then(res => res.json())
            .then(data => success(data))
            .catch(err => fail(err));
    })
})