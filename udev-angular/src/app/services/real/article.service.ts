import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {environment} from '../../../environments/environment';
import {Article} from '../../models/article';
import {ArticleRepository} from '../article.repository';

@Injectable()
export class ArticleService implements ArticleRepository {
  private url: string = environment.api + '/articles';

  constructor(private http: HttpClient) { }

  add(article: Article): Observable<Article> {
    return this.http.post<Article>(this.url, article);
  }

  all(): Observable<Article[]> {
    return this.http.get<Article[]>(this.url);
  }

  byId(id: string): Observable<Article> {
    return this.http.get<Article>(`${this.url}/${id}`);
  }
}
