
document.querySelector("form").addEventListener("submit", function (event) {
    const imageFile = document.getElementById("imageFile").files[0];

    if (!imageFile) {
        alert("Image is required.");
        event.preventDefault();
        return;
    }

    if (!imageFile.type.startsWith("image/")) {
        alert("Invalid file type. Only image files are allowed.");
        event.preventDefault();
        return;
    }

    if (imageFile.size > 5 * 1024 * 1024) { // 5 MB limit
        alert("File size exceeds the 5MB limit.");
        event.preventDefault();
        return;
    }
});
