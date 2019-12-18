import {User} from './user';

export interface Article {
  id: string;
  title: string;
  message: string,
  author: User;
}
