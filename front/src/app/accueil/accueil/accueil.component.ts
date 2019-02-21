import {Component} from '@angular/core';
import {VideoService} from '../../service/video.service';

@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.scss']
})
export class AccueilComponent {

  pathVideogen?: string;

  submitOk = false;
  btnDisabled = false;

  constructor(private readonly videoService: VideoService) {}

  submit() {

    this.videoService.configureVideoGen(this.pathVideogen).then(() => {
      this.submitOk = true;
      this.btnDisabled = false;
    });

    this.btnDisabled = true;
  }

}
