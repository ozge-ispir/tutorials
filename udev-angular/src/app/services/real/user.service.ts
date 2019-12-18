import {Injectable} from '@angular/core';
import {UserRepository} from '../user.repository';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {User} from '../../models/user';
import {Observable} from 'rxjs';

@Injectable()
export class UserService implements UserRepository {
  private url: string = environment.api + '/users';

  constructor(private http: HttpClient) {
  }

  all(): Observable<User[]> {
    return this.http.get<User[]>(this.url);
  }
}
