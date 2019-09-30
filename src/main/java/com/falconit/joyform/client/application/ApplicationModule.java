package com.falconit.joyform.client.application;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2016 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.falconit.joyform.client.application.about.AboutModule;
import com.falconit.joyform.client.application.addins.AddinsModule;
import com.falconit.joyform.client.application.apps.AppsModule;
import com.falconit.joyform.client.application.charts.ChartsModule;
import com.falconit.joyform.client.application.form.FormModule;
import com.falconit.joyform.client.application.home.HomeModule;
import com.falconit.joyform.client.application.login.LoginModule;
import com.falconit.joyform.client.application.menu.MenuModule;
import com.falconit.joyform.client.application.profile.ProfileModule;
import com.falconit.joyform.client.application.registration.RegistrationModule;
import com.falconit.joyform.client.application.tasks.TasksModule;
import com.falconit.joyform.client.application.users.UsersModule;

public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class,
                ApplicationView.class, ApplicationPresenter.MyProxy.class);

        install(new MenuModule());
        install(new AboutModule());
        install(new AddinsModule() );
        install(new LoginModule() );
        install(new UsersModule() );
        install(new ChartsModule() );
        install(new TasksModule() );
        install(new FormModule() );
        install(new AppsModule() );
        install(new HomeModule() );
        install(new ProfileModule() );
        install(new RegistrationModule() );
    }
}
