import {Component, OnInit} from '@angular/core';
import {VideoService} from "../../service/video.service";

@Component({
  selector: 'app-configurator',
  templateUrl: './configurator.component.html',
  styleUrls: ['./configurator.component.scss']
})
export class ConfiguratorComponent implements OnInit {

  constructor(
    private readonly videoService: VideoService
  ) {
  }

  ngOnInit() {
    // this.videoService.getVideoGen()
    //   .then(it => console.log('TODO preparer forumlaire', it));
  }

}
