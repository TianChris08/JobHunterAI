const app = document.getElementById('app');
const pageTitle = document.getElementById('page-title');

const routes = {
    '/chat': {
        title: 'Asistente IA',
        view: '/views/chat.html',
        script: '/js/chat.js'
    },

    '/experience': {
        title: 'Mi experiencia profesional',
        view: '/views/experience.html',
        script: '/js/experience.js'
    },

    '/education': {
        title: 'Educación',
        view: null
    },

    '/skills': {
        title: 'Habilidades',
        view: null
    },

    '/languages': {
        title: 'Idiomas',
        view: null
    },

    '/certifications': {
        title: 'Certificaciones',
        view: null
    },

    '/courses': {
        title: 'Cursos',
        view: null
    },

    '/projects': {
        title: 'Proyectos',
        view: null
    }
};


async function navigate(path, addToHistory = true) {

    const route = routes[path];

    if (!route) {
        navigate('/chat');
        return;
    }

    if (addToHistory) {
        history.pushState({}, '', path);
    }

    pageTitle.textContent = route.title;

    updateActiveNavigation(path);

    if (!route.view) {
        app.innerHTML = `
            <div class="coming-soon">
                <h1>${route.title}</h1>
                <p>Esta sección estará disponible próximamente.</p>
            </div>
        `;

        return;
    }

    try {

        const response = await fetch(route.view);

        if (!response.ok) {
            throw new Error(
                `No se pudo cargar la vista: ${response.status}`
            );
        }

        app.innerHTML = await response.text();

        loadScript(route.script);

    } catch (error) {

        console.error(error);

        app.innerHTML = `
            <div class="error-page">
                <h1>Error</h1>
                <p>No se pudo cargar esta sección.</p>
            </div>
        `;
    }
}


function updateActiveNavigation(path) {

    document.querySelectorAll('[data-route]').forEach(item => {
        item.classList.toggle(
            'active',
            item.dataset.route === path
        );
    });
}


function loadScript(src) {

    const oldScript = document.querySelector(
        `script[data-view-script]`
    );

    if (oldScript) {
        oldScript.remove();
    }

    const script = document.createElement('script');

    script.src = src;
    script.dataset.viewScript = 'true';

    document.body.appendChild(script);
}


document.addEventListener('click', event => {

    const navigation = event.target.closest('[data-route]');

    if (!navigation) {
        return;
    }

    event.preventDefault();

    navigate(navigation.dataset.route);
});


window.addEventListener('popstate', () => {

    navigate(window.location.pathname, false);

});


const initialPath = routes[window.location.pathname]
    ? window.location.pathname
    : '/chat';

navigate(initialPath, false);