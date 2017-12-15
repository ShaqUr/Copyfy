import { Playlist } from './playlist';

export interface User {
    id?: number;
    version?: number;
    username: string;
    password: string;
    email?: string;
    role?: string;
    playlists?: Playlist[];
  }