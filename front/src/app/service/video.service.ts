import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class VideoService {
  private readonly url = '/api/video';

  constructor(private http: HttpClient) {
  }

  public getVideo(videoName: string) {
    // TODO récupérer la video
  }

  public generateVideo(videoName: string[]) {
    // TODO demander de générer la video
  }

  public getVideoGen() {
    // TODO récupérer la configuration du videogen
  }


}
