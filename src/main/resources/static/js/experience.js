const experienceList =
    document.getElementById("experienceList");

const formContainer =
    document.getElementById("experienceFormContainer");

const form =
    document.getElementById("experienceForm");

const formTitle =
    document.getElementById("formTitle");

const addButton =
    document.getElementById("addExperienceButton");

const closeButton =
    document.getElementById("closeFormButton");

const cancelButton =
    document.getElementById("cancelButton");

const currentlyWorking =
    document.getElementById("currentlyWorking");

const endDate =
    document.getElementById("endDate");


let editingId = null;


/*
 * TEMPORAL
 *
 * Esto después desaparecerá.
 *
 * Aquí simulamos los datos que vendrían
 * de GET /api/experiences
 */
let experiences = [

    {
        id: 1,

        company: "Pensisoft",

        position: "Desarrollador de software",

        type: "Prácticas",

        startDate: "2025-09",

        endDate: "2025-12",

        currentlyWorking: false,

        description:
            "Desarrollo de una aplicación de gestión de albaranes para la empresa.",

        responsibilities:
            "Desarrollo de funcionalidades y trabajo con Kotlin y Jetpack Compose.",

        achievements:
            "Desarrollo de una aplicación funcional durante el periodo de prácticas.",

        userNotes:
            ""
    }

];


/* =========================
   RENDER
========================= */

function renderExperiences() {

    if (experiences.length === 0) {

        experienceList.innerHTML = `
            <div class="empty-state">

                <strong>
                    Aún no tienes experiencias profesionales
                </strong>

                Añade tu primera experiencia para empezar
                a construir tu perfil.

            </div>
        `;

        return;
    }


    experienceList.innerHTML =
        experiences
            .map(createExperienceCard)
            .join("");
}


/* =========================
   CARD
========================= */

function createExperienceCard(experience) {

    const initial =
        experience.company
            ? experience.company
                .charAt(0)
                .toUpperCase()
            : "?";


    const start =
        formatDate(experience.startDate);

    const end =
        experience.currentlyWorking
            ? "Actualidad"
            : formatDate(experience.endDate);


    return `

        <article
            class="experience-card"
            data-id="${experience.id}">

            <div class="experience-top">

                <div class="experience-company">

                    <div class="company-icon">
                        ${initial}
                    </div>

                    <div>

                        <div class="company-name">
                            ${escapeHtml(experience.company)}
                        </div>

                        <div class="position">
                            ${escapeHtml(experience.position)}
                            ·
                            ${escapeHtml(experience.type)}
                        </div>

                        <div class="dates">
                            ${start} — ${end}
                        </div>

                    </div>

                </div>


                <div class="experience-menu">

                    <button
                        class="icon-button"
                        onclick="editExperience(${experience.id})"
                        title="Editar">

                        ✎

                    </button>

                </div>

            </div>


            ${
        experience.description
            ? `
                        <div class="experience-description">
                            ${escapeHtml(experience.description)}
                        </div>
                    `
            : ""
    }


            <div class="experience-details">

                ${
        experience.responsibilities
            ? `
                            <div>

                                <div class="detail-title">
                                    Responsabilidades
                                </div>

                                <div class="detail-text">
                                    ${escapeHtml(
                experience.responsibilities
            )}
                                </div>

                            </div>
                        `
            : ""
    }


                ${
        experience.achievements
            ? `
                            <div>

                                <div class="detail-title">
                                    Logros
                                </div>

                                <div class="detail-text">
                                    ${escapeHtml(
                experience.achievements
            )}
                                </div>

                            </div>
                        `
            : ""
    }

            </div>


            <div class="experience-actions">

                <button
                    class="action-button"
                    onclick="editExperience(${experience.id})">

                    Editar

                </button>


                <button
                    class="action-button delete"
                    onclick="deleteExperience(${experience.id})">

                    Eliminar

                </button>

            </div>

        </article>

    `;
}


/* =========================
   FORM
========================= */

function openNewExperienceForm() {

    editingId = null;

    form.reset();

    formTitle.textContent =
        "Nueva experiencia";

    formContainer.classList.remove("hidden");

    endDate.disabled = false;

    formContainer.scrollIntoView({
        behavior: "smooth",
        block: "start"
    });
}


function editExperience(id) {

    const experience =
        experiences.find(
            experience => experience.id === id
        );


    if (!experience) {
        return;
    }


    editingId = id;


    document.getElementById("company").value =
        experience.company;

    document.getElementById("position").value =
        experience.position;

    document.getElementById("type").value =
        experience.type;

    document.getElementById("startDate").value =
        experience.startDate;

    document.getElementById("endDate").value =
        experience.endDate || "";

    document.getElementById("description").value =
        experience.description || "";

    document.getElementById("responsibilities").value =
        experience.responsibilities || "";

    document.getElementById("achievements").value =
        experience.achievements || "";

    document.getElementById("userNotes").value =
        experience.userNotes || "";

    currentlyWorking.checked =
        experience.currentlyWorking;

    endDate.disabled =
        experience.currentlyWorking;


    formTitle.textContent =
        "Editar experiencia";


    formContainer.classList.remove("hidden");


    formContainer.scrollIntoView({
        behavior: "smooth",
        block: "start"
    });
}


function closeForm() {

    formContainer.classList.add("hidden");

    editingId = null;

    form.reset();

    endDate.disabled = false;
}


/* =========================
   SAVE
========================= */

function saveExperience(event) {

    event.preventDefault();


    const experience = {

        company:
            document.getElementById("company")
                .value
                .trim(),

        position:
            document.getElementById("position")
                .value
                .trim(),

        type:
        document.getElementById("type")
            .value,

        startDate:
        document.getElementById("startDate")
            .value,

        endDate:
            currentlyWorking.checked
                ? null
                : document.getElementById("endDate")
                    .value,

        currentlyWorking:
        currentlyWorking.checked,

        description:
            document.getElementById("description")
                .value
                .trim(),

        responsibilities:
            document.getElementById("responsibilities")
                .value
                .trim(),

        achievements:
            document.getElementById("achievements")
                .value
                .trim(),

        userNotes:
            document.getElementById("userNotes")
                .value
                .trim()
    };


    if (
        !experience.company ||
        !experience.position
    ) {

        alert(
            "La empresa y el puesto son obligatorios."
        );

        return;
    }


    /*
     * EDITAR
     */

    if (editingId !== null) {

        const index =
            experiences.findIndex(
                experience =>
                    experience.id === editingId
            );


        if (index !== -1) {

            experiences[index] = {

                id: editingId,

                ...experience

            };

        }

    }


    /*
     * CREAR
     */

    else {

        experiences.unshift({

            id: Date.now(),

            ...experience

        });

    }


    renderExperiences();

    closeForm();
}


/* =========================
   DELETE
========================= */

function deleteExperience(id) {

    const experience =
        experiences.find(
            experience =>
                experience.id === id
        );


    if (!experience) {
        return;
    }


    const confirmed =
        confirm(
            `¿Quieres eliminar la experiencia de ${experience.company}?`
        );


    if (!confirmed) {
        return;
    }


    experiences =
        experiences.filter(
            experience =>
                experience.id !== id
        );


    renderExperiences();
}


/* =========================
   CURRENTLY WORKING
========================= */

currentlyWorking.addEventListener(
    "change",
    () => {

        endDate.disabled =
            currentlyWorking.checked;

        if (currentlyWorking.checked) {
            endDate.value = "";
        }

    }
);


/* =========================
   EVENTS
========================= */

addButton.addEventListener(
    "click",
    openNewExperienceForm
);


closeButton.addEventListener(
    "click",
    closeForm
);


cancelButton.addEventListener(
    "click",
    closeForm
);


form.addEventListener(
    "submit",
    saveExperience
);


/* =========================
   HELPERS
========================= */

function formatDate(value) {

    if (!value) {
        return "—";
    }


    const [year, month] =
        value.split("-");


    return new Date(
        Number(year),
        Number(month) - 1,
        1
    ).toLocaleDateString(
        "es-ES",
        {
            month: "short",
            year: "numeric"
        }
    );
}


function escapeHtml(value) {

    const div =
        document.createElement("div");

    div.textContent =
        value ?? "";

    return div.innerHTML;
}


/* =========================
   INITIAL LOAD
========================= */

renderExperiences();