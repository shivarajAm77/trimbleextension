window.addEventListener("DOMContentLoaded", () => {
  const btn = document.getElementById("fetchProject");
  const output = document.getElementById("output");

  btn.addEventListener("click", async () => {
    try {
      // Example: Get project details from Trimble Connect Workspace API
      const project = await tc?.workspace?.getProject();
      output.textContent = "Project details:\n" + JSON.stringify(project, null, 2);

      // Example: Send data to Virtuele backend
      const response = await fetch("https://clbdev.virtuele.us/api/trimble/project-sync", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(project)
      });

      const result = await response.json();
      output.textContent += "\n\nResponse from Virtuele:\n" + JSON.stringify(result, null, 2);
    } catch (error) {
      output.textContent = "Error: " + error.message;
    }
  });
});
