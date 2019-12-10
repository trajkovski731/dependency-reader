/*
 * Copyright 2014-2018 the original author or authors.
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

/* global SBA */
import Dependencies from './Dependencies';
// end::customization-ui-toplevel[]

// tag::customization-ui-endpoint[]
SBA.use({
  install({viewRegistry}) {
    viewRegistry.addView({
      name: 'instances/dependencies',
      parent: 'instances', // <1>
      path: 'dependencies',
      component: Dependencies,
      label: 'Dependencies',
      order: 1000,
      isEnabled: () => true // <2>
    });
  }
});
// end::customization-ui-endpoint[]
