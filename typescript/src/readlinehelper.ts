import * as readline from 'readline';

export const createReadlineInterface = (reader: NodeJS.ReadableStream, writer: NodeJS.WritableStream) => {
    return readline.createInterface({
        terminal: false,
        input: reader,
        output: writer
    })
}

export const readLineOnClose = (writer: NodeJS.WritableStream, readLineObj : any) => {
    readLineObj.on('close', () => {
        writer.end();
        return
    });
}

export const println = (ln: string, readLineObj:any) => {
    readLineObj.output.write(ln);
    readLineObj.output.write('\n');
}