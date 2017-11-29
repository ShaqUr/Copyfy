import { CopfyClientPage } from './app.po';

describe('copfy-client App', function() {
  let page: CopfyClientPage;

  beforeEach(() => {
    page = new CopfyClientPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
