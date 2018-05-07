/*************************GO-LICENSE-START*********************************
 * Copyright 2015 ThoughtWorks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *************************GO-LICENSE-END***********************************/

package com.thoughtworks.go.plugin.infra.monitor;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public abstract class AbstractDefaultPluginJarLocationMonitorTest {
    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();

    protected File tempSource;

    protected void waitAMoment() throws InterruptedException {
        Thread.yield();
        Thread.sleep(2000);
    }

    protected void copyPluginToThePluginDirectory(File pluginDir, String destinationFilenameOfPlugin) throws IOException, URISyntaxException {
        URL resource = getClass().getClassLoader().getResource("defaultFiles/descriptor-aware-test-plugin.jar");

        FileUtils.copyURLToFile(resource, tempSource);

        File destination = new File(pluginDir, destinationFilenameOfPlugin);
        Files.move(tempSource.toPath(), destination.toPath(), REPLACE_EXISTING);
    }

    protected void updateFileContents(File someFile) {
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(someFile);
            IOUtils.write("some rubbish", output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(output);
        }
    }

    protected PluginFileDetails pluginFileDetails(File directory, String pluginFile, boolean bundledPlugin) {
        return new PluginFileDetails(new File(directory, pluginFile), bundledPlugin);
    }

    @Before
    public void setUp() throws Exception {
        temporaryFolder.create();
        tempSource = temporaryFolder.newFile("temp-file-in-plugin-monitor-test");
    }

    @After
    public void tearDown() throws Exception {
        temporaryFolder.delete();
    }
}
