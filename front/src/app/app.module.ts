import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {RandomComponent} from "./accueil/random/random.component";
import {ConfiguratorComponent} from "./accueil/configurator/configurator.component";

@NgModule({
  declarations: [
    AppComponent,
    RandomComponent,
    ConfiguratorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
