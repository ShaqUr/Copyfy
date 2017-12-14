import { Song } from './song';

export interface Playlist {
    name: string;
    songs?: Song[];
}