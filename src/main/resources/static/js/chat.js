const input = document.getElementById('input');
const messages = document.getElementById('messages');
const welcome = document.getElementById('welcome');
const mic = document.getElementById('mic');

let recording = false;
let sending = false;

function resize(el) {
    el.style.height = 'auto';
    el.style.height = Math.min(el.scrollHeight, 120) + 'px';
}

function key(e) {
    if (e.key === 'Enter' && !e.shiftKey) {
        e.preventDefault();
        send();
    }
}

function suggest(text) {
    input.value = text;
    resize(input);
    send();
}

function add(text, type) {

    const row = document.createElement('div');

    row.className = 'msg ' + type;

    row.innerHTML =
        '<div class="avatar">' +
        (type === 'ai' ? '✦' : '●') +
        '</div>' +
        '<div class="bubble"></div>';

    row.querySelector('.bubble').textContent = text;

    messages.appendChild(row);

    scrollToBottom();
}

function addLoading() {

    const row = document.createElement('div');

    row.className = 'msg ai';
    row.id = 'loading-message';

    row.innerHTML =
        '<div class="avatar">✦</div>' +
        '<div class="bubble">Pensando...</div>';

    messages.appendChild(row);

    scrollToBottom();
}

function removeLoading() {

    const loading = document.getElementById('loading-message');

    if (loading) {
        loading.remove();
    }
}

function scrollToBottom() {

    const chat = document.getElementById('chat');

    chat.scrollTop = chat.scrollHeight;
}

async function send() {

    if (sending) {
        return;
    }

    const text = input.value.trim();

    if (!text) {
        return;
    }

    if (welcome) {
        welcome.style.display = 'none';
    }

    add(text, 'user');

    input.value = '';
    resize(input);

    sending = true;

    addLoading();

    try {

        const response = await fetch('/api/chat', {

            method: 'POST',

            headers: {
                'Content-Type': 'application/json'
            },

            body: JSON.stringify({
                message: text
            })
        });

        if (!response.ok) {
            throw new Error('Error HTTP ' + response.status);
        }

        const data = await response.json();

        removeLoading();

        add(data.response, 'ai');

    } catch (error) {

        console.error(error);

        removeLoading();

        add(
            'Ha ocurrido un error al conectar con la IA. Revisa la consola de Spring Boot.',
            'ai'
        );

    } finally {

        sending = false;
        input.focus();
    }
}

function newChat() {

    messages.innerHTML = '';

    if (welcome) {
        messages.appendChild(welcome);
        welcome.style.display = 'block';
    }
}

function toggleMic() {

    recording = !recording;

    mic.classList.toggle('recording', recording);

    mic.textContent = recording ? '■' : '🎙';

    if (recording) {

        input.placeholder = 'Escuchando… (demo)';

        setTimeout(() => {

            recording = false;

            mic.classList.remove('recording');

            mic.textContent = '🎙';

            input.placeholder =
                'Escribe algo o pulsa el micrófono…';

        }, 1800);
    }
}