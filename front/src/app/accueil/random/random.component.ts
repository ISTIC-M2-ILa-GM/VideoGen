import {Component, OnInit} from '@angular/core';
import {VideoService} from "../../service/video.service";

@Component({
  selector: 'app-random',
  templateUrl: './random.component.html',
  styleUrls: ['./random.component.scss']
})
export class RandomComponent implements OnInit {

  videoUrl: String | undefined;

  constructor(private readonly videoService: VideoService) {
  }

  ngOnInit() {
    // Demande la génération de la video avec un tableau vide pour signifier une video aléatoire
    this.videoService.generateVideo([]).then(it =>
      this.videoUrl = it.value
    );
  }


}
