package com.medium.plugin

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import java.io.File
import java.io.FileWriter
import java.io.IOException

class MyAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        // Get the current project
        val project: Project? = e.project
        if (project == null) {
            Messages.showErrorDialog("No project found!", "Error")
            return
        }

        // Prompt the user for input
        val userInput = Messages.showInputDialog(project, "Enter text to write to the file:", "Input Text", Messages.getQuestionIcon())

        // If the user input is not empty, proceed to write it to the file
        if (!userInput.isNullOrEmpty()) {
            // Find the project directory
            val projectDir: VirtualFile? = project.baseDir
            if (projectDir == null) {
                Messages.showErrorDialog("Cannot find the project directory!", "Error")
                return
            }

            // Specify the file to write to
            val file = File(projectDir.path + "/output.txt")

            // Write the user input to the file
            try {
                FileWriter(file, true).use { writer ->
                    writer.write(userInput + "\n")
                }
                Messages.showInfoMessage("Text successfully written to output.txt!", "Success")
            } catch (ex: IOException) {
                Messages.showErrorDialog("Failed to write to file: ${ex.message}", "Error")
            }
        } else {
            Messages.showErrorDialog("Input cannot be empty.", "Error")
        }
    }
}