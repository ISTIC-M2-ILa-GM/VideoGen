import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {VideoGenConfig} from "../accueil/dto/video-gen-config";
import {ValueWrapper} from "../accueil/dto/value-wrapper";


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
  public generateVideo(videoName: string[]): Promise<ValueWrapper<string>> {
    return this.http
      .post<ValueWrapper<string>>(`${this.url}/generate`, videoName)
      .toPromise();
  }

  /**
   * récupère la configuration pour générer un formulaire
   */
  public getVideoGen(): Promise<VideoGenConfig> {
    return this.http
      .get<VideoGenConfig>(`${this.url}/config`)
      .toPromise();
  }


}
