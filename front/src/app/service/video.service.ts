import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ValueWrapper} from '../accueil/dto/value-wrapper';

@Injectable({
  providedIn: 'root'
})
export class VideoService {
  private readonly url = '/api';

  constructor(private http: HttpClient) {
  }

  /**
   * Demande la génération d'une video et récupère le nom de cette dernière
   */
  generateRandomVideo(): Promise<ValueWrapper<string>> {
    return this.http
      .get<ValueWrapper<string>>(`${this.url}/random`)
      .toPromise()
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
   * Définit le path du fichier videogen pour le serveur
   * @param pathVideogen
   */
  configureVideoGen(pathVideogen: string): Promise<void> {
    return this.http
      .post<void>(`${this.url}/config`, new ValueWrapper(pathVideogen))
      .toPromise();
  }
}
