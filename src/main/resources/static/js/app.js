const base = "/api/applications";
const id = 1;

function saveCoverLetter() {
    fetch(`${base}/${id}/coverletter`, {
        method: "PUT",
        headers: { "Content-Type": "text/plain" },
        body: document.getElementById("previewCoverLetter").value
    });
}

function previewCoverLetter() {
    const data = {
        applicant: {
            senderName: document.getElementById("cl_sender_name").value,
            senderStreet: document.getElementById("cl_sender_street").value,
            senderCity: document.getElementById("cl_sender_city").value
        },
        recipient: {
            receiverName: document.getElementById("cl_receiver_name").value,
            receiverStreet: document.getElementById("cl_receiver_street").value,
            receiverCity: document.getElementById("cl_receiver_city").value
        },
        subject: document.getElementById("cl_subject").value,
        text: document.getElementById("cl_text").value
    };

    fetch(`${base}/${id}/coverletter/preview`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
    .then(res => res.text())
    .then(html => {
        document.getElementById("previewCoverLetter").srcdoc = html;
    });
}

function uploadCvPhoto() {
    const fileInput = document.getElementById("cv_photo");
    const file = fileInput.files[0];

    if (!file) {
        alert("Bitte ein Bild auswählen");
        return;
    }

    const formData = new FormData();
    formData.append("file", file);

    fetch(`${base}/${id}/cv/photo`, {
        method: "POST",
        body: formData
    })
    .then(res => {
        if (!res.ok) throw new Error("Upload fehlgeschlagen");
        return res.text();
    })
    .then(() => {
        previewCv(); // 👉 ganz wichtig: neu rendern
    })
    .catch(err => alert(err.message));
}

function addJob() {
    const container = document.getElementById("jobsContainer");

    const div = document.createElement("div");
    div.classList.add("job");

    div.innerHTML = `
        <input class="job-title" placeholder="Titel">
        <input class="job-company" placeholder="Firma">
        <input class="job-tech" placeholder="Tech">
        <input class="job-start" placeholder="Start">
        <input class="job-end" placeholder="Ende">
        <textarea class="job-tasks" placeholder="Tasks (eine pro Zeile)"></textarea>
    `;

    container.appendChild(div);
}

function previewCv() {

    const data = {
        contactData: {
            cvContactName: document.getElementById("cv_name")?.value || "",
			cvContactBirthday: document.getElementById("cv_birthday")?.value || "",
            cvContactMail: document.getElementById("cv_email")?.value || "",
            cvContactPhone: document.getElementById("cv_phone")?.value || "",
            cvContactStreet: document.getElementById("cv_street")?.value || "",
			cvContactCity: document.getElementById("cv_city")?.value || ""
        },
		jobs: collectJobs(),
        techStack: document.getElementById("cv_tech").value.split("\n")
    };

    fetch(`${base}/${id}/cv/preview`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
    .then(res => res.text())
    .then(html => {
        document.getElementById("previewCv").srcdoc = html;
    });
}

function collectJobs() {
    const jobElements = document.querySelectorAll(".job");

    return Array.from(jobElements).map(job => ({
        title: job.querySelector(".job-title")?.value || "",
        company: job.querySelector(".job-company")?.value || "",
        tech: job.querySelector(".job-tech")?.value || "",
        startDate: job.querySelector(".job-start")?.value || "",
        endDate: job.querySelector(".job-end")?.value || "",
        tasks: (job.querySelector(".job-tasks")?.value || "")
                .split("\n")
                .filter(t => t.trim() !== "")
    }));
}

function updateColor() {
    const color = document.getElementById("colorPicker").value;

    fetch(`${base}/${id}/cv/color?color=${color}`, {
        method: "PATCH"
    });
}

function generatePdf() {
    fetch(`${base}/${id}/generate-pdf`, { method: "POST" });
}

function createApplicationPdf() {
    document.getElementById("pdfPreview").src =
        `${base}/${id}/pdf`;
}