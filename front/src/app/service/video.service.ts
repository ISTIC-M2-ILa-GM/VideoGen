import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ValueWrapper} from '../accueil/dto/value-wrapper';
import {VideoGenConfig} from '../accueil/dto/video-gen-config';

@Injectable({
  providedIn: 'root'
})
export class VideoService {
  private readonly url = '/api';

  constructor(private http: HttpClient) {
  }

  /**
   * génère une variante de video en fonction de la config
   * @param videoName
   */
  generateVideo(videoName: string[]): Promise<ValueWrapper<string>> {
    return this.http
      .post<ValueWrapper<string>>(`${this.url}/generate`, videoName)
      .toPromise();
  }

  /**
   * récupère la configuration pour générer un formulaire
   */
  getVideoGen(): Promise<VideoGenConfig> {
    return this.http
      .get<VideoGenConfig>(`${this.url}/config`)
      .toPromise();
  }

}
