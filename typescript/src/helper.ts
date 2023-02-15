export const splitFirstSpace = (s: string) => {
    var pos = s.indexOf(' ');
    if(pos === -1) {
        return [s];
    }
    return [s.substr(0, pos), s.substr(pos+1)]
}