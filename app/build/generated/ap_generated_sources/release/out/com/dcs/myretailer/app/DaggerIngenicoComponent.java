// Generated by Dagger (https://dagger.dev).
package com.dcs.myretailer.app;

import dagger.internal.Preconditions;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DaggerIngenicoComponent implements IngenicoComponent {
  private DaggerIngenicoComponent() {

  }

  public static Builder builder() {
    return new Builder();
  }

  public static IngenicoComponent create() {
    return new Builder().build();
  }

  @Override
  public void inject(RemarkMainActivity sampleActivity) {
  }

  public static final class Builder {
    private Builder() {
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This method is a no-op. For more, see https://dagger.dev/unused-modules.
     */
    @Deprecated
    public Builder ingenicoModule(IngenicoModule ingenicoModule) {
      Preconditions.checkNotNull(ingenicoModule);
      return this;
    }

    public IngenicoComponent build() {
      return new DaggerIngenicoComponent();
    }
  }
}