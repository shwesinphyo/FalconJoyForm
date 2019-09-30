package com.falconit.joyform.client.application.home.welcome;

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


import com.falconit.joyform.client.application.ApplicationPresenter;
import com.falconit.joyform.client.event.SetPageTitleEvent;
import com.falconit.joyform.client.place.NameTokens;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;


public class WelcomePresenter extends Presenter<WelcomePresenter.MyView, WelcomePresenter.MyProxy> {
    interface MyView extends View {
    }

    @NameToken(NameTokens.welcome)
    @ProxyStandard
    interface MyProxy extends ProxyPlace<WelcomePresenter> {
    }

    @Inject
    WelcomePresenter(
        EventBus eventBus,
        MyView view,
        MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);
    }

    @Override
    protected void onReveal() {
        super.onReveal();
        SetPageTitleEvent.fire("Paged DataTable", "Using MaterialDataPager, you can easily turn your datatable into paged datatable which is good for large and finite data sets.", "datatable/paged/PagedDataTable", "https://material.io/guidelines/components/data-tables.html", this);
    }
}

