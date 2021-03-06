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
package com.thoughtworks.go.buildsession;

import com.thoughtworks.go.domain.BuildCommand;
import com.thoughtworks.go.domain.materials.DirectoryCleaner;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class CleandirCommandExecutor implements BuildCommandExecutor {
    @Override
    public boolean execute(BuildCommand command, BuildSession buildSession) {
        File dir = buildSession.resolveRelativeDir(command.getWorkingDirectory(), command.getStringArg("path"));
        String[] allowed = command.getArrayArg("allowed");
        if (allowed.length == 0) {
            try {
                FileUtils.cleanDirectory(dir);
            } catch (IOException e) {
                return false;
            }
        } else {
            DirectoryCleaner cleaner = new DirectoryCleaner(dir, buildSession.processOutputStreamConsumer());
            cleaner.allowed(allowed);
            cleaner.clean();
        }
        return true;
    }
}
