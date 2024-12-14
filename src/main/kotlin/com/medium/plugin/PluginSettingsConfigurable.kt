package com.medium.plugin

import com.intellij.openapi.options.Configurable
import javax.swing.*
import com.intellij.ui.components.JBTextField

class PluginSettingsConfigurable : Configurable {

    private var settingField: JBTextField? = null
    private var userSetting: String = "default"  // Default setting

    override fun getDisplayName(): String {
        return "Plugin Settings"
    }

    override fun createComponent(): JComponent {
        // Create the UI components
        val panel = JPanel()
        panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)

        val label = JLabel("Enter your custom setting:")
        settingField = JBTextField(userSetting)

        val saveButton = JButton("Save")
        saveButton.addActionListener {
            userSetting = settingField?.text.orEmpty()
            saveSettings()
        }

        panel.add(label)
        panel.add(settingField)
        panel.add(saveButton)

        return panel
    }

    override fun isModified(): Boolean {
        // Return true if settings have changed
        return userSetting != settingField?.text
    }

    override fun apply() {
        // Save the settings when Apply is clicked
        userSetting = settingField?.text.orEmpty()
        saveSettings()
    }

    override fun reset() {
        // Reset the fields to the saved settings
        settingField?.text = userSetting
    }

    private fun saveSettings() {
        // Implement saving logic (e.g., store the setting to disk or project-level settings)
        // For this example, just show a confirmation message
        JOptionPane.showMessageDialog(null, "Settings saved: $userSetting")
    }
}
