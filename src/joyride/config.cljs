(ns joyride.config
  (:require ["os" :as os]
            ["path" :as path]
            ["process" :as process]
            [joyride.db :as db]))

(def user-config-path (or (aget process/env "VSCODE_JOYRIDE_USER_CONFIG_PATH") 
                          (path/join (os/homedir) ".config")))
(def user-scripts-path (path/join "joyride" "scripts"))

(defn user-abs-scripts-path 
  "Returns the absolute path to the User scripts directory
   It's a function because `workspace-abs-scripts-path` is."
  []
  (path/join user-config-path user-scripts-path))

(defn user-abs-src-path
  "Returns the absolute path to the User src directory"
  []
  (path/join user-config-path "joyride" "src"))

(def workspace-scripts-path (path/join ".joyride" "scripts"))

(defn workspace-abs-scripts-path
  "Returns the absolute path to the Workspace scripts directory
   returns `nil` if there is not workspace root"
  []
  (when-let [workspace-root (:workspace-root-path @db/!app-db)]
    (path/join workspace-root workspace-scripts-path)))

(defn workspace-abs-src-path
  "Returns the absolute path to the WOrkspace src directory"
  []
  (when-let [workspace-root (:workspace-root-path @db/!app-db)]
    (path/join workspace-root (path/join ".joyride" "src"))))