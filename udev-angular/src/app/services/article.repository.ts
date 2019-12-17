import {Observable} from 'rxjs';
import {Article} from '../models/article';

export abstract class ArticleRepository {
  abstract add(subject: Article): Observable<Article>;

  abstract all(): Observable<Article[]>;

  abstract byId(id: string): Observable<Article>;
}
