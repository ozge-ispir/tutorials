import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {environment} from '../../../environments/environment';
import {Article} from '../../models/article';
import {ArticleRepository} from '../article.repository';

@Injectable()
export class BookService implements ArticleRepository {
  private url: string = environment.api + '/books';

  constructor(private http: HttpClient) { }

  add(book: Article): Observable<Article> {
    return this.http.post<Article>(this.url, book);
  }

  all(): Observable<Article[]> {
    return this.http.get<Article[]>(this.url);
  }

  byId(id: string): Observable<Article> {
    return this.http.get<Article>(`${this.url}/${id}`);
  }
}
