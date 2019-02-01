import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AccueilComponent} from './accueil/accueil.component';
import {RandomComponent} from "./random/random.component";
import {ConfiguratorComponent} from "./configurator/configurator.component";

const routes: Routes = [
  {path: '', component: AccueilComponent},
  {path: 'random', component: RandomComponent},
  {path: 'configurator', component: ConfiguratorComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AccueilRoutingModule {
}
