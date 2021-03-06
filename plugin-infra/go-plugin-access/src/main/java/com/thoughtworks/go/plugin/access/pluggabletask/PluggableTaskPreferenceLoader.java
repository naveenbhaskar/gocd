/*
 * Copyright 2019 ThoughtWorks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.thoughtworks.go.plugin.access.pluggabletask;

import com.thoughtworks.go.plugin.infra.PluginChangeListener;
import com.thoughtworks.go.plugin.infra.PluginManager;
import com.thoughtworks.go.plugin.infra.plugininfo.GoPluginDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Deprecated
@Component
public class PluggableTaskPreferenceLoader implements PluginChangeListener {

    private PluginManager pluginManager;
    private TaskExtension taskExtension;
    private static final Logger LOGGER = LoggerFactory.getLogger(PluggableTaskPreferenceLoader.class);

    @Autowired
    public PluggableTaskPreferenceLoader(PluginManager pluginManager, TaskExtension taskExtension) {
        this.pluginManager = pluginManager;
        this.taskExtension = taskExtension;
        pluginManager.addPluginChangeListener(this);
    }

    private void loadTaskConfigIntoPreferenceStore(GoPluginDescriptor descriptor) {
        if (taskExtension.canHandlePlugin(descriptor.id())) {
            taskExtension.doOnTask(descriptor.id(), (task, pluginDescriptor) -> PluggableTaskConfigStore.store().setPreferenceFor(pluginDescriptor.id(), new TaskPreference(task)));
        }
    }

    @Override
    public void pluginLoaded(GoPluginDescriptor descriptor) {
        loadTaskConfigIntoPreferenceStore(descriptor);
    }

    @Override
    public void pluginUnLoaded(GoPluginDescriptor descriptor) {
        PluggableTaskConfigStore.store().removePreferenceFor(descriptor.id());
    }
}
