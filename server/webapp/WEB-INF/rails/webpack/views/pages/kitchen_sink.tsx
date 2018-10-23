/*
 * Copyright 2018 ThoughtWorks, Inc.
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

import * as m from 'mithril';
import * as Icons from "../components/icons/index";
import * as Buttons from "../components/buttons/index";
import {HelloWorld} from "../components/hello_world/index";
import {KeyValuePair} from "../components/key_value_pair/index";
import {MithrilComponent} from "../../jsx/mithril-component";
import {CollapsiblePanel} from "../components/collapsible_panel/index";

const HeaderPanel = require('views/components/header_panel');

export = class KitchenSink extends MithrilComponent<null> {
  view() {
    return (
      <main class="main-container">
        <HeaderPanel title="Kitchen Sink"/>
        <h3>CollapsiblePanel</h3>
        <CollapsiblePanel header={<div>Collapsible Panel header</div>}
                          actions={<button>foo</button>}>
          <div> Anything can go in the body</div>
        </CollapsiblePanel>
        <hr/>

        <h3>Icons</h3>
        <Icons.Settings/>
        <Icons.Analytics/>
        <Icons.Edit/>
        <Icons.Clone/>
        <Icons.Delete/>
        <Icons.Lock/>
        <hr/>

        <HelloWorld id="foo"/>
        <br/>

        <div>
          <h1>Work in progress components!</h1>
        </div>

        <h3>Buttons:</h3>
        <Buttons.Primary>Primary Button</Buttons.Primary>
        <Buttons.Secondary>Secondary Button</Buttons.Secondary>
        <Buttons.Reset>Reset Button</Buttons.Reset>
        <Buttons.Primary>Save</Buttons.Primary>
        <Buttons.Reset>Cancel</Buttons.Reset>

        <br/>
        <h3>Small Buttons:</h3>
        <Buttons.Primary small={true}>Small Primary Button</Buttons.Primary>
        <Buttons.Secondary small={true}>Small Secondary Button</Buttons.Secondary>
        <Buttons.Reset small={true}>Small Reset Button</Buttons.Reset>
        <Buttons.Primary small={true}>Save</Buttons.Primary>
        <Buttons.Reset small={true}>Cancel</Buttons.Reset>

        <h3>Some examples of accordions</h3>

        <h3>Some examples of key value pairs</h3>
        <KeyValuePair data={
          {
            'First Name': 'Jon',
            'Last Name':  'Doe',
            'email':      'jdoe@example.com',
          }
        }/>

        <h3>Some examples of inline key value pairs</h3>
        <KeyValuePair inline={true} data={
          {
            'Plugin':  'my-fancy-plugin-name',
            'version': 'v1.0.0-24536'
          }
        }/>
      </main>
    );
  }
};