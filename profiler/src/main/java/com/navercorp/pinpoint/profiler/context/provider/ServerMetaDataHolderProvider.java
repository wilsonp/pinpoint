/*
 * Copyright 2017 NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.navercorp.pinpoint.profiler.context.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.navercorp.pinpoint.bootstrap.context.ServerMetaDataHolder;
import com.navercorp.pinpoint.profiler.AgentInfoSender;
import com.navercorp.pinpoint.profiler.context.DefaultServerMetaDataHolder;
import com.navercorp.pinpoint.profiler.util.RuntimeMXBeanUtils;

import java.util.List;

/**
 * @author Woonduk Kang(emeroad)
 */
public class ServerMetaDataHolderProvider implements Provider<ServerMetaDataHolder> {

    private final AgentInfoSender agentInfoSender;

    @Inject
    public ServerMetaDataHolderProvider(AgentInfoSender agentInfoSender) {
        this.agentInfoSender = agentInfoSender;
    }

    @Override
    public ServerMetaDataHolder get() {
        List<String> vmArgs = RuntimeMXBeanUtils.getVmArgs();
        ServerMetaDataHolder serverMetaDataHolder = new DefaultServerMetaDataHolder(vmArgs);
        serverMetaDataHolder.addListener(agentInfoSender);
        return serverMetaDataHolder;
    }
}
