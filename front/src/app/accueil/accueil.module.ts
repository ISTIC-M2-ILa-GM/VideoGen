import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {AccueilRoutingModule} from './accueil-routing.module';
import {AccueilComponent} from './accueil/accueil.component';
import {RandomComponent} from "./random/random.component";
import {VideoComponent} from "./video/video.component";
import {ConfiguratorComponent} from "./configurator/configurator.component";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AccueilComponent,
    RandomComponent,
    ConfiguratorComponent,
    VideoComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    AccueilRoutingModule
  ]
})
export class AccueilModule {
}
