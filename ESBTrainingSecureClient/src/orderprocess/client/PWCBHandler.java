/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *   * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package orderprocess.client;

import org.apache.ws.security.WSPasswordCallback;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import java.io.IOException;

public class PWCBHandler implements CallbackHandler {


    /**
     * Field key
     */

    private static final byte[] key = {

        (byte) 0x31, (byte) 0xfd, (byte) 0xcb, (byte) 0xda, (byte) 0xfb,

        (byte) 0xcd, (byte) 0x6b, (byte) 0xa8, (byte) 0xe6, (byte) 0x19,

        (byte) 0xa7, (byte) 0xbf, (byte) 0x51, (byte) 0xf7, (byte) 0xc7,

        (byte) 0x3e, (byte) 0x80, (byte) 0xae, (byte) 0x98, (byte) 0x51,

        (byte) 0xc8, (byte) 0x51, (byte) 0x34, (byte) 0x04,

    };

    @SuppressWarnings("deprecation")
	public void handle(Callback[] callbacks)

        throws IOException, UnsupportedCallbackException {


        for (int i = 0; i < callbacks.length; i++) {

            if (callbacks[i] instanceof WSPasswordCallback) {

                WSPasswordCallback pc = (WSPasswordCallback) callbacks[i];

    
                if (pc.getUsage() == WSPasswordCallback.USERNAME_TOKEN_UNKNOWN) {

                    if (pc.getIdentifier().equals("Ron") && pc.getPassword().equals("noR")) {
                        return;
                    }

                    if (pc.getIdentifier().equals("joe") && pc.getPassword().equals("eoj")) {
                        return;
                    }

                    if (pc.getPassword().equals("sirhC")) {
                        return;
                    }

                    throw new UnsupportedCallbackException(callbacks[i],"check failed");
                }


                if (pc.getUsage() == WSPasswordCallback.KEY_NAME) {
                    pc.setKey(key);
                } else if (pc.getIdentifier().equals("alice")) {
                    pc.setPassword("password");
                } else if (pc.getIdentifier().equals("bob")) {
                    pc.setPassword("password");
                } else if (pc.getIdentifier().equals("Ron")) {
                    pc.setPassword("noR");
                } else if (pc.getIdentifier().equals("joe")) {
                    pc.setPassword("eoj");
                } else if (pc.getIdentifier().equals("ip")) {
                    pc.setPassword("password");
                } else {
                    pc.setPassword("sirhC");
                }
            } else {

                throw new UnsupportedCallbackException(callbacks[i],
                    "Unrecognized Callback");
            }

        }

    }

}