# CI Readme

## Release

To create a new release make a PR from `develop` into `main`. Once this is merged if the version number has changed a signed build will then be created and pushed to maven central. If all checks pass this will go live automatically (This may take up to 48 hours).

Once this is complete create a mergeback from `main` into `develop` to keep both branches in sync.