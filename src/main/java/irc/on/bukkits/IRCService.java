/**
 *   IRCService API
 *   Copyright 2012 Matt Baxter
 * 
 * ******************************
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
 */
package irc.on.bukkits;

import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class IRCService {

    JavaPlugin plugin;

    public void disable() {
        if (plugin != null) {
            plugin.getServer().getServicesManager().unregisterAll(plugin);
        }
    }

    public void enable(JavaPlugin plugin, IRCService service) {
        if (plugin == null || service == null) {
            throw new RuntimeException("Attempt to initialize IRCService with null values");
        }
        this.plugin = plugin;
        plugin.getServer().getServicesManager().register(irc.on.bukkits.IRCService.class, service, plugin, ServicePriority.Normal);
    }

    public abstract void sendMessageToChannel(String channel, String message);
}
