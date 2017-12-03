import { Playlist } from './playlist';

export interface User {
    username: string;
    password: string;
    email?: string;
    role?: string;
    playlists?: Playlist[];
  }