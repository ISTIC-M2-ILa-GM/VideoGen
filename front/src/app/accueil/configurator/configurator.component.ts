import {Component, OnInit} from '@angular/core';
import {VideoService} from "../../service/video.service";
import {Video} from '../dto/video';

@Component({
  selector: 'app-configurator',
  templateUrl: './configurator.component.html',
  styleUrls: ['./configurator.component.scss']
})
export class ConfiguratorComponent implements OnInit {
  generator?: Array<Array<Video>>;

  constructor(private readonly videoService: VideoService) {
  }

  ngOnInit() {
    this.videoService.getConfigurator()
      .then(it => this.generator = it);
  }

}
