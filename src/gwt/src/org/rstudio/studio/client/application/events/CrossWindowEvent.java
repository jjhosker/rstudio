/*
 * CrossWindowEvent.java
 *
 * Copyright (C) 2009-15 by RStudio, Inc.
 *
 * Unless you have received this program directly from RStudio pursuant
 * to the terms of a commercial license agreement with RStudio, then
 * this program is licensed to you under the terms of version 3 of the
 * GNU Affero General Public License. This program is distributed WITHOUT
 * ANY EXPRESS OR IMPLIED WARRANTY, INCLUDING THOSE OF NON-INFRINGEMENT,
 * MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE. Please refer to the
 * AGPL (http://www.gnu.org/licenses/agpl-3.0.txt) for more details.
 *
 */
package org.rstudio.studio.client.application.events;

import org.rstudio.core.client.StringUtil;
import org.rstudio.studio.client.RStudioGinjector;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

// A CrossWindowEvent can be fired from the main window to a satellite, or vice
// versa. Note that CrossWindowEvents should be annotated with 
// @JavaScriptSerializable so that they can be appropriately marshaled across
// the window boundary.
public abstract class CrossWindowEvent<T extends EventHandler> 
                      extends GwtEvent<T>
{
   public CrossWindowEvent()
   {
      setOriginWindowName(
            RStudioGinjector.INSTANCE.getSatellite().getSatelliteName());
   }

   // Whether the event should be forwarded to the main window by default 
   public boolean forward()
   {
      return true;
   }
   
   public boolean isFromMainWindow()
   {
      return StringUtil.isNullOrEmpty(originWindowName_);
   }
   
   public String originWindowName()
   {
      return originWindowName_;
   }
   
   public void setOriginWindowName(String originWindow)
   {
      originWindowName_ = originWindow;
   }
   
   private String originWindowName_;
}
