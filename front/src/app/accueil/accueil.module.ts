import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {AccueilRoutingModule} from './accueil-routing.module';
import {AccueilComponent} from './accueil/accueil.component';
import {VideoComponent} from './video/video.component';
import {ConfiguratorComponent} from './configurator/configurator.component';
import {RandomComponent} from './random/random.component';

@NgModule({
  declarations: [AccueilComponent, VideoComponent, ConfiguratorComponent, RandomComponent],
  imports: [
    CommonModule,
    AccueilRoutingModule
  ]
})
export class AccueilModule { }
